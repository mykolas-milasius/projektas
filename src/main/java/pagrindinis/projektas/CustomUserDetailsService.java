package pagrindinis.projektas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
 
public class CustomUserDetailsService implements UserDetailsService
{ 
    @Autowired
    private UserRepository user_repository;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    {
        User user = user_repository.findByEmail(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("Vartotojas nerastas");
        }
        
        UserDetails userDetail;
        
        if(user.getRoleNames().contains("ADMIN"))
        {
            userDetail = org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
    				.password(user.getPassword())
            		.roles("ADMIN")
                    .build();
        }
        else
        {
            userDetail = org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
    				.password(user.getPassword())
            		.roles("USER")
                    .build();
        }
        
		return userDetail;
    }
}