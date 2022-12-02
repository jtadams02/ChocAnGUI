package jt.chocolic;
/**
 * Stores information of services provided by the provider.
 *
 * @author Jackson Harper
 */
public class ProviderService {
    private ProviderAccount provider;
    private Service service;

    public ProviderService() {
        provider = new ProviderAccount();
        service = new Service();
    }

    public ProviderService(ProviderAccount prov, Service serv) {
        provider = prov;
        service = serv;
    }

    public ProviderAccount getProvider() {
        return provider;
    }

    public void setProvider(ProviderAccount prov) {
        provider = prov;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service serv) {
        service = serv;
    }

}