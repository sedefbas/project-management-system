package management.sedef.help.service.ımpl;

import management.sedef.help.exception.HelpCommentNotFoundException;
import management.sedef.help.model.Help;
import management.sedef.help.model.enums.HelpStatus;
import management.sedef.help.model.mapper.HelpRequestToDomainMapper;
import management.sedef.help.model.request.HelpRequest;
import management.sedef.help.port.HelpDeletePort;
import management.sedef.help.port.HelpReadPort;
import management.sedef.help.port.HelpSavePort;
import management.sedef.help.service.HelpService;
import management.sedef.minio.payload.BucketNameEnum;
import management.sedef.minio.payload.FileResponse;
import management.sedef.minio.service.MinioService;
import management.sedef.minio.util.FileTypeUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class HelpServiceImpl implements HelpService {

    private final HelpReadPort readPort;
    private final HelpSavePort savePort;
    private final HelpDeletePort deletePort;
    private final HelpRequestToDomainMapper helpRequestToDomainMapper = HelpRequestToDomainMapper.initialize();
    private final MinioService minioService;

    @Override
    public Help createHelp(HelpRequest request, MultipartFile photo) {

        String bucketName = BucketNameEnum.HELP_PHOTO.getBucketName();
        String fileType = FileTypeUtils.getFileType(photo);
        List<String> screenshotUrls = new ArrayList<>();

        if (fileType != null) {
            FileResponse fileResponse = minioService.putObject(photo, bucketName, fileType);
            String photoUrl = minioService.getObjectUrl(bucketName, fileResponse.getFilename());
            screenshotUrls.add(photoUrl);
        }

        Help help = helpRequestToDomainMapper.map(request);
        help.setCreatedAt(LocalDateTime.now());
        help.setScreenshotUrls(screenshotUrls); // Liste olarak ayarla
        return savePort.save(help);
    }


    @Override
    public List<Help> getHelpsByProjectId(Integer projectId) {
        return readPort.findByProjectId(projectId);
    }

    @Override
    public List<Help> getHelpsByProjectIdAndStatus(Integer projectId, HelpStatus helpStatus) {
        return readPort.findByProjectIdAndHelpStatus(projectId, helpStatus);
    }


    @Override
    public Help getHelpById(String helpId) {
        return readPort.findById(helpId)
                .orElseThrow(() -> new HelpCommentNotFoundException("Help not found with id: " + helpId));
    }

    @Override
    public void deleteHelp(String helpId) {
        deletePort.deleteById(helpId);
    }
}
