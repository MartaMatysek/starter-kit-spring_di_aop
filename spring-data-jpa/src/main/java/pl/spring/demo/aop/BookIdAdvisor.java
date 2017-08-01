package pl.spring.demo.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

import pl.spring.demo.annotation.NullableBookId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.IdAware;

public class BookIdAdvisor implements MethodBeforeAdvice{

	private Sequence sequence;
	
	@Override
    public void before(Method method, Object[] methodParamethres, Object classContainigMethod) throws Throwable {

        if (hasAnnotation(method, classContainigMethod, NullableBookId.class)) {
            checkNullId(classContainigMethod, methodParamethres[0]);
        }
    }

    private void checkNullId(Object methodParamethers, Object book) {
        if (book instanceof IdAware && ((IdAware) book).getId() == null) {
        	BookDaoImpl bookImpl = (BookDaoImpl) methodParamethers;
        	((BookTo) book).setId(sequence.nextValue(bookImpl.findAll()));
        }
    }

    private boolean hasAnnotation(Method method, Object methodParamethers, Class annotationClass) throws NoSuchMethodException {
        boolean hasAnnotation = method.getAnnotation(annotationClass) != null;

        if (!hasAnnotation && methodParamethers != null) {
            hasAnnotation = methodParamethers.getClass().getMethod(method.getName(), method.getParameterTypes()).getAnnotation(annotationClass) != null;
        }
        return hasAnnotation;
    }
    

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }
	
}
