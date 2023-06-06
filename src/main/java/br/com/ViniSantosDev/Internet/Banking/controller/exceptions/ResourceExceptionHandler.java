//package br.com.ViniSantosDev.Internet.Banking.controller.exceptions;
//
//import br.com.ViniSantosDev.Internet.Banking.service.exceptions.DataBaseException;
//import br.com.ViniSantosDev.Internet.Banking.service.exceptions.ResourceException;
//import io.swagger.models.auth.In;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import javax.servlet.http.HttpServletRequest;
//import java.time.Instant;
//
//@ControllerAdvice
//public class ResourceExceptionHandler {
//
//    @ExceptionHandler(ResourceException.class)
//    public ResponseEntity<StandardError> entityNotFound(ResourceException e, HttpServletRequest request) {
//        HttpStatus status = HttpStatus.BAD_REQUEST;
//        StandardError error = new StandardError();
//        error.setTimestamp(Instant.now());
//        error.setStatus(HttpStatus.NOT_FOUND.value());
//        error.setError("Resource not found");
//        error.setMessage(e.getMessage());
//        error.setPath(request.getRequestURI());
//        return ResponseEntity.status(status).body(error);
//    }
//
//    @ExceptionHandler(DataBaseException.class)
//    public ResponseEntity<StandardError> database(DataBaseException e, HttpServletRequest request) {
//        HttpStatus status = HttpStatus.BAD_REQUEST;
//        StandardError error = new StandardError();
//        error.setTimestamp(Instant.now());
//        error.setStatus(HttpStatus.BAD_REQUEST.value());
//        error.setError("DataBase Exception");
//        error.setMessage(e.getMessage());
//        error.setPath(request.getRequestURI());
//        return ResponseEntity.status(status).body(error);
//    }
//}
