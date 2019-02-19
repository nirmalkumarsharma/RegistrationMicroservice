package in.nks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nks.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Integer>
{
	AppUser findByEmail(String email);
}
