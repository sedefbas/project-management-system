package management.sedef.minio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileResponseException extends RuntimeException {

    public FileResponseException(String msg) {
        super(msg);
    }
}

