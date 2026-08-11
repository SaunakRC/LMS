package LearningSystem.Service.Serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LearningSystem.Entity.Wishlist;
import LearningSystem.Repository.WishlistRepository;
import LearningSystem.Service.WishlistService;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wrepo;

    @Override
    public Wishlist addToWishlist(int userId, int courseId) {

        Wishlist existing =
                wrepo.findByUserIdAndCourseId(userId, courseId);

        if (existing != null) {
            return existing;
        }

        Wishlist wishlist = new Wishlist();

        wishlist.setUserId(userId);
        wishlist.setCourseId(courseId);
        wishlist.setAddedDate(LocalDateTime.now());

        return wrepo.save(wishlist);
    }

    @Override
    public List<Wishlist> getMyWishlist(int userId) {

        return wrepo.findByUserId(userId);
    }

    @Override
    public String removeFromWishlist(int userId, int courseId) {

        Wishlist wishlist =
                wrepo.findByUserIdAndCourseId(userId, courseId);

        if (wishlist == null) {
            return "Course not found in wishlist";
        }

        wrepo.delete(wishlist);

        return "Course removed from wishlist";
    }

    @Override
    public boolean isInWishlist(int userId, int courseId) {

        return wrepo.findByUserIdAndCourseId(userId, courseId) != null;
    }
}