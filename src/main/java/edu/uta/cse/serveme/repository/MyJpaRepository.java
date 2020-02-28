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

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author housirvip
 */
@NoRepositoryBean
public class MyJpaRepository<T, ID> extends SimpleJpaRepository<T, ID> {

    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager em;

    @Autowired
    public MyJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
    }

    /**
     * common function：save/update selective
     * if field is null, then ignore it, rather than save to DB as null
     *
     * @param entity S
     * @return S
     */
    @Nonnull
    @Override
    @Transactional(rollbackFor = Exception.class)
    public <S extends T> S save(@Nonnull S entity) {
        // get ID
        ID entityId = (ID) entityInformation.getId(entity);
        Optional<T> optionalT;
        if (StringUtils.isEmpty(entityId)) {
            // mark as new record
            optionalT = Optional.empty();
        } else {
            // entity id != null, find it
            optionalT = findById(entityId);
        }
        //set empty fields as null
        String[] nullProperties = getNullProperties(entity);
        optionalT.ifPresentOrElse(target -> {
            // copy properties with BeanUtils
            BeanUtils.copyProperties(entity, target, nullProperties);
            // update fields which is not null
            em.merge(target);
        }, () -> {
            // create new one
            em.persist(entity);
        });
        // check if id is present
        return entity;
    }

    /**
     * get bean's null fields
     *
     * @param src Object
     * @return String[]
     */
    private static String[] getNullProperties(Object src) {
        // get Bean
        BeanWrapper srcBean = new BeanWrapperImpl(src);
        // get Bean null fields
        Set<String> properties = new HashSet<>();
        // get Bean PropertyDescriptor
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : pds) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = srcBean.getPropertyValue(propertyName);
            // if null or ""
            if (StringUtils.isEmpty(propertyValue)) {
                srcBean.setPropertyValue(propertyName, null);
                properties.add(propertyName);
            }
        }
        return properties.toArray(new String[0]);
    }
}
