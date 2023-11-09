package BilgeAdam.Repository;

import BilgeAdam.Entity.Ders;
import BilgeAdam.Entity.Ogretmen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DersRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public DersRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Ders> findAll() {
        String sql = "SELECT * FROM \"DERS\"";
        return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ders.class));
    }

    public Ders findById(long id) {
        String sql = "Select * FROM \"DERS\" WHERE \"ID\" = :searchedId";
        Map<String, Object> params = new HashMap<>();
        params.put("searchedId", id);

        return namedParameterJdbcTemplate.queryForObject(sql, params, BeanPropertyRowMapper.newInstance(Ders.class));
    }

    public boolean save(Ders ders) {
        String sql = "insert into \"public\".\"DERS\" (\"OGRETMEN_ID\", \"KONU_ID\") values (:ogretmenId, :konuId)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ogretmenId", ders.getOgretmen().getId());
        paramMap.put("konuId", ders.getKonu().getId());
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }

    public boolean deleteById(long id) {
        String sql = "DELETE FROM \"DERS\" where \"ID\" = :ID";
        Map<String, Object> params = new HashMap<>();
        params.put("ID", id);

        return namedParameterJdbcTemplate.update(sql, params) == 1;
    }
}
