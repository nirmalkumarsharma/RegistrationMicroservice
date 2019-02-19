package in.nks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.nks.entity.AppUser;
import in.nks.json.entity.RegisteredEmail;
import in.nks.json.entity.ResponseMessage;
import in.nks.service.AppUserService;

@RestController
@CrossOrigin
@RequestMapping(value="/user", consumes=MediaType.ALL_VALUE)
public class AppUserController
{
	@Autowired
	private AppUserService appUserService;
	
	@RequestMapping(value="/register", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseMessage registerAppUser(@RequestBody AppUser appUser)
	{
		String message="";
		if(appUserService.getAppUser(appUser.getEmail())!=null)
		{
			message="Email already in use";
		}
		else
		{
			BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
			appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
			appUserService.saveAppUser(appUser);
			message="User registered successfully";
		}
		ResponseMessage responseMessage=new ResponseMessage();
		responseMessage.setMessage(message);
		return responseMessage;
	}
	
	@RequestMapping(value="/retrieve", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AppUser retrieveAppUser(@RequestBody RegisteredEmail registeredemail)
	{
		AppUser appUser=appUserService.getAppUser(registeredemail.getEmail());
		if(appUser==null)
		{
			return null;
		}
		else
		{
			return appUser;
		}
	}
	
	@RequestMapping(value="/status", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseMessage isWorking()
	{
		String message="Working";
		ResponseMessage responseMessage=new ResponseMessage();
		responseMessage.setMessage(message);
		return responseMessage;
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AppUser> retrieveAll()
	{
		return appUserService.getAllAppUser();
	}
}
