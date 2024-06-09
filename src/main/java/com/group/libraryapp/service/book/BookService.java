package com.group.libraryapp.service.book;
import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository,UserLoanHistoryRepository userLoanHistoryRepository,UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository=userLoanHistoryRepository;
        this.userRepository=userRepository;

    }

    public void saveBook(BookCreateRequest request) {
        Book b=bookRepository.save(new Book(request.getName()));
    }
    public void loanBook(BookLoanRequest request){
        Book book= bookRepository.findByName(request.getBookName()).orElseThrow(IllegalArgumentException::new);
        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(),false)){
            throw new IllegalArgumentException("already used");
        }
        User user=userRepository.findByName(request.getUserName());
        if(user==null){
            throw new IllegalArgumentException("no user");
        }
        userLoanHistoryRepository.save(new UserLoanHistory(user,book.getName(),false));
    }
    @Transactional
    public void returnBook(BookReturnRequest request){
        User user=userRepository.findByName(request.getUserName());
        if(user==null){
            throw new IllegalArgumentException("no user");
        }
        UserLoanHistory history = userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request.getBookName()).orElseThrow(IllegalArgumentException::new);
        history.doReturn();
    }
}
