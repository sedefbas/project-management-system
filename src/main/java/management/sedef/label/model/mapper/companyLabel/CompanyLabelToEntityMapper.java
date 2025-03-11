package management.sedef.label.model.mapper.companyLabel;

import management.sedef.label.model.CompanyLabel;
import management.sedef.label.model.entity.CompanyLabelEntity;
import management.sedef.common.model.entity.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyLabelToEntityMapper extends BaseMapper<CompanyLabel, CompanyLabelEntity> {
    static CompanyLabelToEntityMapper initialize(){
        return Mappers.getMapper(CompanyLabelToEntityMapper.class);
    }
}
