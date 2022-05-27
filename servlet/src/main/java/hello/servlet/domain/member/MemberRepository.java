package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConurrentHashMap AtomicLong 사용 고려
 */
public class MemberRepository {

    private Map<Long, Member> store = new HashMap<>();
    private long sequnce = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
        // 싱글톤 패턴 이므로 생성자를 막아놓는다
    }

    public Member save(Member member) {
        member.setId(++sequnce);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        // store의 모든 값들을 꺼낸후 새로운 ArrayList에 담아서 리턴
        // ArrayList의 값을 조작해도 store를 보호하기 위함
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
