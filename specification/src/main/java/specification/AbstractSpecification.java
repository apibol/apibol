package specification;

public abstract class AbstractSpecification<T> implements Specification<T> {

	public abstract Boolean isSatisfiedBy(T instance);

	@Override
	public Specification<T> and(final Specification<T> specification) {
		return new AndSpecification<T>(this, specification);
	}

	@Override
	public Specification<T> or(final Specification<T> specification) {
		return new OrSpecification<T>(this, specification);
	}

	@Override
	public Specification<T> not(final Specification<T> specification) {
		return new NotSpecification<T>(specification);
	}

}