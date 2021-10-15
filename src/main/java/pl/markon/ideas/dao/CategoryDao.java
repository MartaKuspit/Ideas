package pl.markon.ideas.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.markon.ideas.model.Category;
import pl.markon.ideas.model.Question;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDao {

    private static Logger LOG = Logger.getLogger(CategoryDao.class.getName());
    private ObjectMapper objectMapper;


    public CategoryDao() {
        this.objectMapper = new ObjectMapper();
    }
    private List<Category> getCategories() {
        try {
            return objectMapper.readValue(Files.readString(Paths.get("./categories.txt")), new TypeReference<>() {
            });
        } catch (IOException e) {
           // e.printStackTrace();
            LOG.log(Level.WARNING, "Error on getCategories",e);
            return new ArrayList<>();
        }
    }

    public List<Category> findAll() {
        return getCategories();
    }

    public void add(Category category) {

        List<Category> categories = getCategories();
        categories.add(category);
        try {
            Files.writeString(Paths.get("./categories.txt"),  objectMapper.writeValueAsString(categories));
        } catch (IOException e) {
            //e.printStackTrace();
            LOG.log(Level.WARNING, "Error on add category",e);
        }

//        try {
//            List<String> lines = Files.readAllLines(Paths.get("./categories.txt"));
//            lines.add(category.getName());
//            Files.writeString(Paths.get("./categories.txt"), String.join("\n", lines));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public Optional<Category> findOne(String categoryName) {
        return getCategories().stream().filter(c->c.getName().equals(categoryName)).findAny();
    }
}
