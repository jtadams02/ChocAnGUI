package jt.chocolic;
/**
 * Stores information of services provided to a member.
 *
 * @author Jackson Harper
 */
public class MemberService {
    private MemberAccount member;
    private Service service;

    public MemberService() {
        member = new MemberAccount();
        service = new Service();
    }

    public MemberService(MemberAccount mem, Service serv) {
        member = mem;
        service = serv;
    }

    public MemberAccount getMember() {
        return member;
    }

    public void setMember(MemberAccount mem) {
        member = mem;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service serv) {
        service = serv;
    }

}