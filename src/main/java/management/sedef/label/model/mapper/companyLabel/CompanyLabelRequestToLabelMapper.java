package management.sedef.label.model.mapper.companyLabel;

import management.sedef.label.model.Label;
import management.sedef.label.model.request.CompanyLabelRequest;
import management.sedef.common.model.entity.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CompanyLabelRequestToLabelMapper extends BaseMapper<CompanyLabelRequest, Label> {
    static CompanyLabelRequestToLabelMapper initialize(){
        return Mappers.getMapper(CompanyLabelRequestToLabelMapper.class);
    }
}
