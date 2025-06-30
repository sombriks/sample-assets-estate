package sample.assets.estate.endpoints;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import sample.assets.estate.configurations.BaseRequest;
import sample.assets.estate.configurations.TestSecurityConfig;
import sample.assets.estate.models.Asset;
import sample.assets.estate.models.AssetType;
import sample.assets.estate.models.ConsumablePosition;
import sample.assets.estate.repositories.Assets;
import sample.assets.estate.repositories.ConsumablesPosition;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConsumablesTest {

    @Autowired
    private BaseRequest request;
    @Autowired
    private Assets assetsRepository;
    @Autowired
    private ConsumablesPosition repository;

    @Test
    @Order(4)
    public void shouldListConsumables() {
        var result = request.getWithAuth("/consumables/list");
        assertThat(result.getStatusCode().value(), equalTo(200));
        assertThat(result.getBody(),
                allOf(
                        containsString("Paper"),
                        containsString("Sugar"),
                        containsString("Glue"),
                        containsString("Pencil")
                )
        );
        List<Asset> assets = assetsRepository.findByType(AssetType.CONSUMABLE);
        assertThat(assets.size(), equalTo(4));
        List<ConsumablePosition> consumables = repository.findAll();
        assertThat(consumables.size(), equalTo(6));
    }

    @Test
    @Order(1)
    public void shouldCreateConsumable() {
        String data = Arrays.stream("""
                          name=Sugar
                          description=Sugar for coffee
                          unitValue=3
                          amount=100
                          comment=
                          departmentId=1
                        """.split("\n"))
                .reduce("", (acc, a) -> acc += "&" + a.trim());
        var result = request.postWithAuth("/consumables", data);
        assertThat(result.getStatusCode().value(), equalTo(200));
    }

    @Test
    @Order(2)
    public void shouldUpdateConsumable() {
        String data = Arrays.stream("""
                          id=5
                          assetId=4
                          reasonId=7
                          statusId=1
                          name=Sugar
                          description=Sugar for coffee
                          unitValue=3
                          amount=100
                          comment=
                          departmentId=1
                        """.split("\n"))
                .reduce("", (acc, a) -> acc += "&" + a.trim());
        var result = request.putWithAuth("/consumables", data);
        assertThat(result.getStatusCode().value(), equalTo(200));
    }

    @Test
    @Order(3)
    public void shouldNOTUpdateConsumable() {
        String data = Arrays.stream("""
                          assetId=4
                          reasonId=7
                          statusId=1
                          name=Sugar
                          description=Sugar for coffee
                          unitValue=3
                          amount=100
                          comment=
                          departmentId=1
                        """.split("\n"))
                .reduce("", (acc, a) -> acc += "&" + a.trim());
        var result = request.putWithAuth("/consumables", data);
        assertThat(result.getStatusCode().value(), equalTo(400));
    }
}
