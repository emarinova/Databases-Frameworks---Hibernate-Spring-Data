package json.processing.productsshop.utils.beans;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanRegister {

   //@Bean
   //public javax.xml.bind.JAXBContext jaxbContext = JAXBContext.newInstance(Object.getClass());

    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }

}
