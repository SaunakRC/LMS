package LearningSystem.Service;

import java.util.List;

import LearningSystem.Entity.Purchase;

public interface PurchaseService {

    Purchase buyCourse(int userId, int courseId);

    List<Purchase> getMyCourses(int userId);

    boolean hasPurchased(int userId, int courseId);
}