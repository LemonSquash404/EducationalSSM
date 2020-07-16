import com.scorequery.dao.AchievementFormDao;
import com.scorequery.entity.AchievementForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;
import java.util.List;



public class ServiceTest extends BaseTest {

    @Autowired
    AchievementFormDao achievementFormDao;

    private static Logger LOGGER = null;

    @BeforeClass
    public static void setLogger() throws MalformedURLException
    {
        System.setProperty("log4j2.configurationFile","log4j2.properties");
        LOGGER = LogManager.getLogger();
    }
    @Test
    public void cc(){
        List<AchievementForm> achievementForms = achievementFormDao.GetAchievementForm();
        for (AchievementForm achievementForm : achievementForms){
            System.out.println(achievementForm);
        }
    }

}
