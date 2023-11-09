package BilgeAdam.Repository;

import BilgeAdam.Entity.Ogrenci;
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
public class OgrenciRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public OgrenciRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Ogrenci> findAll() {
        String sql = "SELECT * FROM \"OGRENCI\"";
        return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ogrenci.class));
    }

    public Ogrenci findById(long id) {
        String sql = "Select * FROM \"OGRETMEN\" WHERE \"ID\" = :searchedId";
        Map<String, Object> params = new HashMap<>();
        params.put("searchedId", id);

        return namedParameterJdbcTemplate.queryForObject(sql, params, BeanPropertyRowMapper.newInstance(Ogrenci.class));
    }

    public boolean save(Ogrenci ogrenci) {
        String sql = "insert into \"public\".\"OGRENCI\" (\"NAME\", \"OGR_NUMBER\" ,\"YEAR\") values (:name, :ogr_numb, :year)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", ogrenci.getName());
        paramMap.put("ogr_numb", ogrenci.getOgrNumber());
        paramMap.put("year", ogrenci.getYear());

        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }

    public boolean deleteById(long id) {
        String sql = "DELETE FROM \"OGRENCI\" where \"ID\" = :ID";
        Map<String, Object> params = new HashMap<>();
        params.put("ID", id);

        return namedParameterJdbcTemplate.update(sql, params) == 1;
    }
}
