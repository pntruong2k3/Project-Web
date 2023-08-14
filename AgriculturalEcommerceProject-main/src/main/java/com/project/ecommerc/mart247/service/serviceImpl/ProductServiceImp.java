package com.project.ecommerc.mart247.service.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.ecommerc.mart247.DTO.ProductDTO;
import com.project.ecommerc.mart247.entity.CategoryEntity;
import com.project.ecommerc.mart247.entity.ProductEntity;
import com.project.ecommerc.mart247.repository.CategoryRepository;
import com.project.ecommerc.mart247.repository.ProductRepository;
import com.project.ecommerc.mart247.service.ProductService;
import com.project.ecommerc.mart247.util.CommonUtil;

@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Page<ProductEntity> getAllProduct(Pageable pageable) {

		return productRepository.findByDeletedAtIsNull(pageable);
	}

	@Override
	public void saveProduct(ProductDTO productDTO, MultipartFile file) {
		ProductEntity product = new ProductEntity(productDTO);
		Long categoryId = productDTO.getCategoryId();
		Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryId);
		product.setCategory(categoryEntity.get());
		product.setImg(CommonUtil.ConvertImg(file));
		product.getProductDetails().setProduct(product);
		productRepository.save(product);

	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteProductById(id);

	}

	@Override
	public Optional<ProductEntity> findProductById(Long id) {

		return productRepository.findById(id);
	}
	
	@Override
	public ProductDTO findProductDTOById(Long id) {
		ProductEntity entity = productRepository.findById(id).orElseThrow();
		ProductDTO dto =  new ProductDTO(entity);
		return dto;
	}

	@Override
	public List<ProductEntity> getProduct() {

		return productRepository.findAll();
	}

	@Override
	public Optional<ProductEntity> findProductByName(String name) {
		// TODO Auto-generated method stub
		return Optional.of(productRepository.findByName(name));
	}

	public Page<ProductDTO> getEntitiesByName(int page, int pageSize, String sortField, String sortDirection,
			String name) {
		Page<ProductDTO> dtoPage = null;


		Sort sort = Sort.by(sortField);
		sort = sortDirection.equals("desc") ? sort.descending() : sort.ascending();
		Pageable pageable = PageRequest.of(page, pageSize, sort);
		Page<ProductEntity> productEntityPage = productRepository.findByNameContainingIgnoreCaseAndDeletedAtIsNull(name, pageable);
		dtoPage = convertToPageProdDTO(productEntityPage);

		return dtoPage;

	}

	public Page<ProductDTO> getEntities(int page, int pageSize, String sortField, String sortDirection) {
		Page<ProductDTO> dtoPage = null;


		Sort sort = Sort.by(sortField);
		sort = sortDirection.equals("desc") ? sort.descending() : sort.ascending();
		Pageable pageable = PageRequest.of(page, pageSize, sort);
		Page<ProductEntity> productEntityPage = productRepository.findByDeletedAtIsNull(pageable);
		dtoPage = convertToPageProdDTO(productEntityPage);

		return dtoPage;

	}

	private Page<ProductDTO> convertToPageProdDTO(Page<ProductEntity> productEntities) {
		Page<ProductDTO> productDTOS = productEntities.map(entity -> {
			ProductDTO dto = new ProductDTO(entity);
			BeanUtils.copyProperties(dto, entity);
			dto.setQuantity(entity.getQuantity());
			return dto;
		});
		return productDTOS;
	}

	private List<ProductDTO> convertToListProdDTO(List<ProductEntity> productEntities) {
		List<ProductDTO> categoryDTOS = productEntities.stream().map(entity -> {
			ProductDTO dto = new ProductDTO(entity);
			BeanUtils.copyProperties(dto, entity);
			dto.setQuantity(entity.getQuantity());
			return dto;
		}).collect(Collectors.toList());
		return categoryDTOS;
	}

	

	@Override
	public void editProduct(ProductDTO productDTO, MultipartFile file) {
		Optional<ProductEntity> productOptional = productRepository.findById(productDTO.getId());
		ProductEntity product = productOptional.get();
		Optional<CategoryEntity> category = categoryRepository.findById(productDTO.getCategoryId());

		product.setCategory(category.get());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setDiscount(productDTO.getDiscount());
		product.setDescription(productDTO.getDescription());
		product.setQuantity(productDTO.getQuantity());

		String base64Image = CommonUtil.ConvertImg(file);

		Long categoryId = productDTO.getCategoryId();
		// Truy vấn cơ sở dữ liệu hoặc sử dụng phương thức tương ứng để lấy
		// CategoryEntity dựa trên categoryId
		Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryId);

		// Gán CategoryEntity cho thuộc tính categoryEntity trong ProductDTO
		product.setCategory(categoryEntity.get());
		if (!file.isEmpty()) {
			product.setImg(base64Image);
		}
		productRepository.save(product);

	}
	

	@Override
	public int getTotalProduct() {
		int totalOrder = productRepository.findByDeletedAtIsNull().size();
		return totalOrder;
	}
	
	public Page<ProductDTO> getProductByCategory(long id, int page, int pageSize, String sortField, String sortDirection){
		Page<ProductDTO> dtoPage = null;
		
		Sort sort = Sort.by(sortField);
		sort = sortDirection.equals("desc") ? sort.descending() : sort.ascending();
		Pageable pageable = PageRequest.of(page, pageSize, sort);
		Page<ProductEntity> entity = productRepository.findProductByCategory(id, pageable);
		dtoPage = convertToPageProdDTO(entity);
		return dtoPage;
		
	};

	
}
