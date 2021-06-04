
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Zhang
 * @date 6/4/2021 3:12 PM
 * @description
 */
@SpringBootTest(classes = {AutoDeliverApplicationTest.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AutoDeliverApplicationTest {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    public void test() {
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("lagou-service-resume");

        for (int i = 0; i < serviceInstanceList.size(); i++) {
            ServiceInstance serviceInstance = serviceInstanceList.get(i);
            System.out.println(serviceInstance);

        }
    }

}
