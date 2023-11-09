package BilgeAdam.Repository;

import BilgeAdam.Entity.Ders;
import BilgeAdam.Entity.Konu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class KonuRepository {


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public KonuRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Konu> findAll() {
        String sql = "SELECT * FROM \"KONU\"";
        return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Konu.class));
    }

    public Konu findById(long id) {
        String sql = "Select * FROM \"KONU\" WHERE \"ID\" = :searchedId";
        Map<String, Object> params = new HashMap<>();
        params.put("searchedId", id);

        return namedParameterJdbcTemplate.queryForObject(sql, params, BeanPropertyRowMapper.newInstance(Konu.class));
    }

    public boolean save(Konu konu) {
        String sql = "insert into \"public\".\"KONU\" (\"NAME\") values (:dersName)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dersName", konu.getName());

        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }

    public boolean deleteById(long id) {
        String sql = "DELETE FROM \"KONU\" where \"ID\" = :ID";
        Map<String, Object> params = new HashMap<>();
        params.put("ID", id);

        return namedParameterJdbcTemplate.update(sql, params) == 1;
    }
}
