package specification;

public class OrSpecification<T> extends AbstractSpecification<T> {

	private Specification<T> specification1;

	private Specification<T> specification2;

	public OrSpecification(final Specification<T> specification1,
						   final Specification<T> specification2) {
		this.specification1 = specification1;
		this.specification2 = specification2;
	}

	@Override
	public Boolean isSatisfiedBy(T instance) {
		return specification1.isSatisfiedBy(instance)
				|| specification2.isSatisfiedBy(instance);
	}

}