package management.sedef.company.port.adapter;

import lombok.RequiredArgsConstructor;
import management.sedef.company.model.Address;
import management.sedef.company.model.entity.AddressEntity;
import management.sedef.company.model.mapper.addressmapper.AddressEntityToDomainMapper;
import management.sedef.company.model.mapper.addressmapper.AddressToEntityMapper;
import management.sedef.company.port.addressport.AddressReadPort;
import management.sedef.company.port.addressport.AddressSavePort;
import management.sedef.company.repository.AddressRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddressAdapter implements AddressReadPort, AddressSavePort {

    private final AddressRepository repository;
    private final AddressEntityToDomainMapper addressEntityToDomainMapper = AddressEntityToDomainMapper.initialize();
    private final AddressToEntityMapper addressToEntityMapper = AddressToEntityMapper.initialize();


    @Override
    public Optional<Address> findById(Long id) {
        return repository.findById(id).map(addressEntityToDomainMapper::map);
    }

    //todo
    @Override
    public List<Address> findAll() {
        return List.of();
    }

    @Override
    public Address save(Address address) {
        AddressEntity addressEntity = addressToEntityMapper.map(address);
        repository.save(addressEntity);
        return addressEntityToDomainMapper.map(addressEntity);
    }
}
