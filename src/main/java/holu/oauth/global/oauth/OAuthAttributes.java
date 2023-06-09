package holu.oauth.global.oauth;

import holu.oauth.domain.member.Member;
import holu.oauth.domain.member.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registrationId, String memberNameAttributeName, Map<String, Object> attributes    ) {
        return ofGoogle(memberNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String memberNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
            .name((String) attributes.get("name"))
            .email((String) attributes.get("email"))
            .picture((String) attributes.get("picture"))
            .attributes(attributes)
            .nameAttributeKey(memberNameAttributeName)
            .build();
    }

    public Member toEntity() {
        return Member.
            builder()
            .name(name)
            .email(email)
            .picture(picture)
            .role(Role.ROLE_MEMBER)
            .build();
    }
}
