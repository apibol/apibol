package specification;

public class NotSpecification<T> extends AbstractSpecification<T> {

	private Specification<T> specification1;

	public NotSpecification(final Specification<T> specification1) {
		super();
		this.specification1 = specification1;
	}

	@Override
	public Boolean isSatisfiedBy(T instance) {
		return !specification1.isSatisfiedBy(instance);
	}

}