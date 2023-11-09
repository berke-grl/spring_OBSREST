package BilgeAdam.Repository;

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
public class OgretmenRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public OgretmenRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Ogretmen> findAll() {
        String sql = "SELECT * FROM \"OGRETMEN\"";
        return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ogretmen.class));
    }

    public Ogretmen findById(long id) {
        String sql = "Select * FROM \"OGRETMEN\" WHERE \"ID\" = :searchedId";
        Map<String, Object> params = new HashMap<>();
        params.put("searchedId", id);

        return namedParameterJdbcTemplate.queryForObject(sql, params, BeanPropertyRowMapper.newInstance(Ogretmen.class));
    }

    public boolean save(Ogretmen ogretmen) {
        String sql = "insert into \"public\".\"OGRETMEN\" (\"NAME\", \"IS_GICIK\") values (:NAME, :GICIK)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("NAME", ogretmen.getName());
        paramMap.put("GICIK", ogretmen.isGıcık());
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }

    public boolean deleteById(long id) {
        String sql = "DELETE FROM \"OGRETMEN\" where \"ID\" = :ID";
        Map<String, Object> params = new HashMap<>();
        params.put("ID", id);

        return namedParameterJdbcTemplate.update(sql, params) == 1;
    }
}
