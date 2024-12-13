package management.sedef.company.port.adapter;

import lombok.RequiredArgsConstructor;
import management.sedef.company.model.Address;
import management.sedef.company.port.addressport.AddressReadPort;
import management.sedef.company.repository.AddressRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddressAdapter implements AddressReadPort {

    private final AddressRepository addressRepository;


    @Override
    public Optional<Address> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Address> findAll() {
        return List.of();
    }
}
