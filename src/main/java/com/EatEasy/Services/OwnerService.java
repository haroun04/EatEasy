package com.EatEasy.Services;

import com.EatEasy.Models.Owner;
import java.util.List;

public interface OwnerService {
    List<Owner> findAll();

    Owner findById(Long id);

    Owner save(Owner owner);

    void deleteById(Long id);

    Owner update(Long id, Owner owner);
}
