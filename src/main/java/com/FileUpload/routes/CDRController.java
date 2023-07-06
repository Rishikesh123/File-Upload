package com.FileUpload.routes;

import com.FileUpload.POJO.CDRRequest;
import com.FileUpload.models.CDR;
import com.FileUpload.repository.CDRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cdr")
public class CDRController {

    @Autowired
    private CDRRepository cdrRepository;

    @GetMapping
    public Page<CDR> getCDR(@RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "10") int size,
                            @RequestParam(name = "sortBy", defaultValue = "timestamp") String sortBy,
                            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection,
                            @RequestParam(name = "caller", required = false) String caller,
                            @RequestParam(name = "receiver", required = false) String receiver) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Specification<CDR> spec = Specification.where(null);

        if (caller != null) {
            Specification<CDR> callerSpec = (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("caller"), caller);
            spec = spec.and(callerSpec);
        }

        if (receiver != null) {
            Specification<CDR> receiverSpec = (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("receiver"), receiver);
            spec = spec.and(receiverSpec);
        }

        return cdrRepository.findAll(spec, paging);
    }

    @PostMapping
    public Page<CDR> postCDR(@RequestBody CDRRequest request) {
        System.out.println("Inside Post");
        int page = request.getPage();
        int size = request.getSize();
        String sortBy = request.getSortBy();
        String sortDirection = request.getSortDirection();
        String caller = request.getCaller();
        String receiver = request.getReceiver();
        System.out.println("SortDirection : " + sortDirection);
        System.out.println("Caller : " + caller);
        System.out.println("Receiver : " + receiver);

        Sort.Direction direction = sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Specification<CDR> spec = Specification.where(null);

        if (caller != null) {
            System.out.println("Caller : " + caller);
            Specification<CDR> callerSpec = (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("caller"), caller);
            spec = spec.and(callerSpec);
        }

        if (receiver != null) {
            System.out.println("Receiver : " + receiver);
            Specification<CDR> receiverSpec = (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("receiver"), receiver);
            spec = spec.and(receiverSpec);
        }

        System.out.println("Spec : " + spec);
        return cdrRepository.findAll(spec, paging);
    }
}
