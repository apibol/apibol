package specification;

public interface Specification<T> {

	Boolean isSatisfiedBy(T instance);

	Specification<T> and(Specification<T> specification);

	Specification<T> or(Specification<T> specification);

	Specification<T> not(Specification<T> specification);

}