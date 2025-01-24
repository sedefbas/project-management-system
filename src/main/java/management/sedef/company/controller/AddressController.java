package management.sedef.company.controller;

import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.company.model.Address;
import management.sedef.company.model.Company;
import management.sedef.company.model.mapper.addressmapper.AddressToResponseMapper;
import management.sedef.company.model.request.AddressRequest;
import management.sedef.company.model.request.CompanyRequest;
import management.sedef.company.model.response.AddressResponse;
import management.sedef.company.model.response.CompanyResponse;
import management.sedef.company.service.AddressService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final AddressToResponseMapper addressToResponseMapper = AddressToResponseMapper.initialize();

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('address:create')")
    public SuccessResponse<Void> create(@RequestBody AddressRequest request){
        addressService.create(request);
        return SuccessResponse.success();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('address:detail')")
    public SuccessResponse<AddressResponse> findById(@PathVariable Long id){
        Address address = addressService.findById(id);
        AddressResponse addressResponse = addressToResponseMapper.map(address);
        return SuccessResponse.success(addressResponse);
    }


}
