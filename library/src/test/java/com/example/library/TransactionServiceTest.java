package com.example.library;

import com.example.library.Models.*;
import com.example.library.Repositories.TransactionRepository;
import com.example.library.Services.BookService;
import com.example.library.Services.RequestService;
import com.example.library.Services.TransactionService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionService transactionService;

    @Mock
    RequestService requestService;

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    BookService bookService;

    @Test
    public void createTransactionIssueTest() throws Exception {
        Book book = Book.builder()
                .id(2000)
                .name("RandomBook")
                .build();

        Student student = Student.builder()
                .id(3000)
                .name("Raj")
                .build();

        Request request = Request.builder()
                .id(1000)
                .student(student)
                .book(book)
                .requestType(RequestType.ISSUE)
                .build();

        Request returnRequest = Request.builder()
                .requestType(RequestType.RETURN)
                .build();

        Transaction transaction = Transaction.builder()
                .id(4000)
                .transactionId(UUID.randomUUID().toString())
                .transactionDate(new Date(System.currentTimeMillis()))
                .build();

        Book assignedBook = Book.builder()
                .name("RandomBook")
                .id(2000)
                .student(student)
                .build();

        when(transactionRepository.save(any())).thenReturn(transaction);
        when(bookService.createOrUpdateBook(any())).thenReturn(assignedBook);
        String txnId = transactionService.createTransaction(request);

        verify(bookService, times(1)).createOrUpdateBook(any());
        verify(transactionRepository, times(2)).save(any());
        verify(requestService, times(0)).getRequestById(request.getId());
    }

    @Test
    public void createTransactionReturnTest() throws Exception {
        Book book = Book.builder()
                .id(2000)
                .name("RandomBook")
                .build();

        Student student = Student.builder()
                .id(3000)
                .name("Raj")
                .build();

        Request issueRequest = Request.builder()
                .id(1000)
                .student(student)
                .book(book)
                .requestType(RequestType.ISSUE)
                .build();

        Book assignedBook = Book.builder()
                .name("RandomBook")
                .id(2000)
                .student(student)
                .build();

        Request returnRequest = Request.builder()
                .student(student)
                .book(assignedBook)
                .requestType(RequestType.RETURN)
                .build();

        //This returns the last successful issue transaction
        List<Transaction> transactionList = Arrays.asList(
                Transaction.builder()
                        .request(issueRequest)
                        .transactionDate(new Date(1639032314000l))
                        .build()
        );

        assignedBook = Book.builder()
                .name("RandomBook")
                .id(2000)
                .student(null)
                .build();


        when(transactionRepository.findTransactionByRequest_Book_IdAndTransactionStatusOrderByTransactionDateDesc(book.getId(), TransactionStatus.SUCCESS))
                .thenReturn(transactionList);

        String txnId = transactionService.createTransaction(returnRequest);

        verify(bookService, times(1)).createOrUpdateBook(any());
        verify(transactionRepository,times(1)).findTransactionByRequest_Book_IdAndTransactionStatusOrderByTransactionDateDesc(anyInt(),any());
        verify(transactionRepository, times(2)).save(any());
        verify(requestService, times(0)).getRequestById(returnRequest.getId());
    }

    @Test(expected = Exception.class)
    public void createTransactionReturnNegativeTest() throws Exception {
        Book book = Book.builder()
                .id(2000)
                .name("RandomBook")
                .build();

        Student student = Student.builder()
                .id(3000)
                .name("Raj")
                .build();

        Request returnRequest = Request.builder()
                .id(1000)
                .student(student)
                .book(book)
                .requestType(RequestType.RETURN)
                .build();

        Request issueRequest = Request.builder()
                .book(book)
                .requestType(RequestType.RETURN)
                .build();

        Book assignedBook = Book.builder()
                .name("RandomBook")
                .id(2000)
                .student(student)
                .build();

        List<Transaction> transactionList = Arrays.asList(
                Transaction.builder()
                        .request(issueRequest)
                        .transactionDate(new Date(1639032314000l))
                        .build()
        );

        //when(bookService.createOrUpdateBook(any())).thenReturn(assignedBook);
        when(transactionRepository.findTransactionByRequest_Book_IdAndTransactionStatusOrderByTransactionDateDesc(anyInt(),any())).thenReturn(transactionList);

        String txnId = transactionService.createTransaction(returnRequest);

        //verify(bookService, times(1)).createOrUpdateBook(any());
        //verify(transactionRepository, times(2)).save(any());
        //verify(requestService, times(0)).getRequestById(request.getId());
    }
}
