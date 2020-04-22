package rubusdata.location.domain;

class OrderNotFoundException extends RuntimeException {

    OrderNotFoundException(Long id) {
        super("Could not find order " + id);
    }
}
