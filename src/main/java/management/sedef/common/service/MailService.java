package management.sedef.common.service;

import management.sedef.common.model.request.MailSendRequest;

public interface MailService {

    void send(MailSendRequest sendRequest);

}
