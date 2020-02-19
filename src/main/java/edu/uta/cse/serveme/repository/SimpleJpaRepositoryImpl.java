package edu.uta.cse.serveme.repository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author housirvip
 */
@NoRepositoryBean
public class SimpleJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> {

    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager em;

    @Autowired
    public SimpleJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
    }

    /**
     * common function：save/update selective
     *
     * @param entity S
     * @return S
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public <S extends T> S save(S entity) {
        //get ID
        ID entityId = (ID) entityInformation.getId(entity);
        Optional<T> optionalT;
        if (StringUtils.isEmpty(entityId)) {
            // mark as new record
            optionalT = Optional.empty();
        } else {
            //entity id != null, find it
            optionalT = findById(entityId);
        }
        //set empty fields as null
        String[] nullProperties = getNullProperties(entity);
        //check if id is present
        if (!optionalT.isPresent()) {
            //create new one
            em.persist(entity);
        } else {
            //1.get the updated entity
            T target = optionalT.get();
            //2.copy properties with BeanUtils
            BeanUtils.copyProperties(entity, target, nullProperties);
            //3.update fields which is not null
            em.merge(target);
        }
        return entity;
    }

    /**
     * get bean's null fields
     *
     * @param src Object
     * @return String[]
     */
    private static String[] getNullProperties(Object src) {
        //1.get Bean
        BeanWrapper srcBean = new BeanWrapperImpl(src);
        //2.get Bean PropertyDescriptor
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        //3.get Bean null fields
        Set<String> properties = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : pds) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = srcBean.getPropertyValue(propertyName);
            if (StringUtils.isEmpty(propertyValue)) {
                srcBean.setPropertyValue(propertyName, null);
                properties.add(propertyName);
            }
        }
        return properties.toArray(new String[0]);
    }
}
