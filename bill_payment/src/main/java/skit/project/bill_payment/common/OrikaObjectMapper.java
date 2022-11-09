 package skit.project.bill_payment.common;
 import org.springframework.stereotype.Component;

 import ma.glasnost.orika.MapperFacade;
 import ma.glasnost.orika.MapperFactory;
 import ma.glasnost.orika.impl.DefaultMapperFactory;
import skit.project.bill_payment.DTO.BillDTO;
import skit.project.bill_payment.DTO.BillDetailDTO;
import skit.project.bill_payment.DTO.CustomerDTO;
 import skit.project.bill_payment.DTO.OrgnisationDTO;
import skit.project.bill_payment.DTO.PriceDTO;
import skit.project.bill_payment.entity.BillDetailEntity;
import skit.project.bill_payment.entity.BillEntity;
import skit.project.bill_payment.entity.CustomerEntity;
 import skit.project.bill_payment.entity.OrgnisationEntity;
import skit.project.bill_payment.entity.PriceEntity;

 @Component
 public class OrikaObjectMapper {
 	private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
     private MapperFacade mapper=mapperFactory.getMapperFacade();
	
 	static  {
 		mapperFactory.classMap(OrgnisationDTO.class, OrgnisationEntity.class);  
 		mapperFactory.classMap(OrgnisationEntity.class, OrgnisationDTO.class);
 		mapperFactory.classMap(CustomerDTO.class, CustomerEntity.class);
 		mapperFactory.classMap(CustomerEntity.class,CustomerDTO.class);
 		mapperFactory.classMap(PriceDTO.class, PriceEntity.class);
        mapperFactory.classMap(PriceEntity.class,PriceDTO.class);
        mapperFactory.classMap(BillDTO.class, BillEntity.class);
        mapperFactory.classMap(BillEntity.class,BillDTO.class);
        mapperFactory.classMap(BillDetailDTO.class, BillDetailEntity.class);
        mapperFactory.classMap(BillDetailEntity.class,BillDetailDTO.class);
     }
	
	
 	public MapperFacade getMapper() {
 		return mapper;
 	}
 }
