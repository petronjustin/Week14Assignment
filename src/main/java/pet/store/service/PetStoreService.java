package pet.store.service;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {
	@Autowired
	private PetStoreDao petStoreDao;

	public PetStoreData savePetStore(PetStoreData petStoreData) {
		Long petStoreId = petStoreData.getPetStoreId();
		PetStore petStore = findOrCreatePetStore(petStoreId);
		
		
		copyPetStoreFields(petStore, petStoreData);
			return new PetStoreData(petStoreDao.save(petStore));
		
	}

	private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
		petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
		petStore.setPetStorePhone(petStoreData.getPetStorePhone());
		petStore.setPetStoreCity(petStoreData.getPetStoreCity());
		petStore.setPetStoreName(petStoreData.getPetStoreName());
		petStore.setPetStoreId(petStoreData.getPetStoreId());
		petStore.setPetStoreState(petStoreData.getPetStoreState());
		petStore.setPetStoreZip(petStoreData.getPetStoreZip());
	}

	private PetStore findOrCreatePetStore(Long petStoreId) {
		if(Objects.isNull(petStoreId)) {
		return new PetStore();
		
		}
		else {
			return findPetStoreById(petStoreId);
		}
		
	}

	private PetStore findPetStoreById(Long petStoreId) {
		return petStoreDao.findById(petStoreId).orElseThrow(() -> new NoSuchElementException
				("Pet store with ID=" + petStoreId + " was lost in the abysmal hole of the interwebs."));
	}
	
	
	
		
	}

