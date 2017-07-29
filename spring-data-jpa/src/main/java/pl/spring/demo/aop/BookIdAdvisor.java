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
    public void before(Method method, Object[] objects, Object o) throws Throwable {

        if (hasAnnotation(method, o, NullableBookId.class)) {
            checkNullId(o, objects[0]);
        }
    }

    private void checkNullId(Object o, Object book) {
        if (book instanceof IdAware && ((IdAware) book).getId() == null) {
        	BookDaoImpl bookImpl = (BookDaoImpl) o;
        	((BookTo) book).setId(sequence.nextValue(bookImpl.findAll()));
        }
    }

    private boolean hasAnnotation(Method method, Object o, Class annotationClazz) throws NoSuchMethodException {
        boolean hasAnnotation = method.getAnnotation(annotationClazz) != null;

        if (!hasAnnotation && o != null) {
            hasAnnotation = o.getClass().getMethod(method.getName(), method.getParameterTypes()).getAnnotation(annotationClazz) != null;
        }
        return hasAnnotation;
    }
    

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }
	
}
