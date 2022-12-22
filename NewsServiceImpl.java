package com.stackroute.keepnote.service;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.exception.NewsNotFoundExeption;
import com.stackroute.keepnote.model.News;
import com.stackroute.keepnote.model.NewsUser;
import com.stackroute.keepnote.repository.NewsRepository;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn't currently 
* provide any additional behavior over the @Component annotation, but it's a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */
@Service
public class NewsServiceImpl implements NewsService{

	/*
	 * Autowiring should be implemented for the NoteRepository and MongoOperation.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	@Autowired
    private NewsRepository newsRepo;
	private NewsUser newsUser;
	private List<News> newsarr;


    
    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepo = newsRepository;
       // newsRepo.findById(userId);
    }
	
	/*
	 * This method should be used to save a new note.
	 */
	@Override
	public boolean createNews(News news) {
		
			Optional<NewsUser> newsUser = newsRepo.findById(news.getCreatedby());
			if(newsUser.isPresent()) {
				for(News n: newsUser.get().getNewslst()) {
					if(n.getPublishedAt().equalsIgnoreCase(news.getPublishedAt()))
						return false;
				}
				
				newsUser.get().getNewslst().add(news);
				newsRepo.save(newsUser.get());
				return true;
			}else {
				NewsUser newsuser = new NewsUser();
				newsuser.setUserId(news.getCreatedby());
				List<News> news1 = new ArrayList<News>();
			
				news1.add(news);
				newsuser.setNewslst(news1);
				newsRepo.save(newsuser);
				return true;
			}
			
		}
	
	
	
@Override	
public boolean deleteNews(String userId, String title) {
		
		boolean status = false;
		newsUser = new NewsUser();
		newsarr = newsRepo.findById(userId).get().getNewslst();

		if (!newsarr.isEmpty()) {

			Iterator iterator = newsarr.listIterator();
			while (iterator.hasNext()) {

				News news1 = (News) iterator.next();
				if (title.equalsIgnoreCase(news1.getTitle()))
					
					iterator.remove();

			}
			
//			List<News>   newsarr = newsRepo.findById(userId).get().getNewslst();
//			List<News> resultnote = new ArrayList<>();
//			for(News nts: newsarr)
//			{
//				if((nts.getTitle()).equalsIgnoreCase(title))
//				{
//					newsarr.remove(nts);
//				}
//			}
			
			newsUser.setUserId(userId);
			newsUser.setNewslst(newsarr);
			newsRepo.save(newsUser);
			status = true;
		}

		return status;
	}

	
		
//	newsarr = new ArrayList<>();
//	newsUser = new NewsUser();
//	
//   // IF THE NOTEUSER ALREADY EXISTS
//	if (newsRepo.existsById(news.getCreatedby()))
//	{
//		newsUser = newsRepo.findById(news.getCreatedby()).get();
//		newsarr = newsUser.getNewslst();
//		newsarr.add(news);
//		newsUser.setNewslst(newsarr);
//		return true;
//		
//	}
//	
//	//IF NOT, SET USERID AND SETNOTES FOR THE USER
//	else 
//	{
//		newsarr.add(news);
//		newsUser.setUserId(news.getCreatedby());
//		
//		newsUser.setNewslst(newsarr);
//		
//	}
//	
//	NewsUser useradd = newsRepo.save(newsUser);
//	if(useradd != null)
//	return true;
//	
//	return false;
//	
//}

		
	
	/* This method should be used to delete an existing note. */

//	
//	public boolean deleteNote(String userId, int noteId) {
//		
//		return false;
//	}
	
	/* This method should be used to delete all notes with specific userId. */

	
//	public boolean deleteAllNotes(String userId) {
//		
//		return false;
//	}

	/*
	 * This method should be used to update a existing note.
	 */
//	public News updateNote(News note, int id, String userId) throws NewsNotFoundExeption {
//		
//		return null;
//	}

	/*
	 * This method should be used to get a note by noteId created by specific user
	 */
//	public News getNoteByNoteId(String userId, int noteId) throws NewsNotFoundExeption {
//		
//		return null;
//	}

	/*
	 * This method should be used to get all notes with specific userId.
	 */
	

	
	

	@Override
	public List<News> getAllNewsByUserId(String userId) {
		 List<News> neswarticles = newsRepo.findById(userId).get().getNewslst();
		return neswarticles;
		
	}

	

	

}
