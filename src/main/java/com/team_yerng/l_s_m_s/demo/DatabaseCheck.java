//package com.team_yerng.l_s_m_s.demo;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DatabaseCheck implements CommandLineRunner{
//    private final JdbcTemplate jdbcTemplate;
//
//    public DatabaseCheck(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        try {
//            Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
//            System.out.println("✅ SQL Server connection successful! Test query returned: " + result);
//        } catch (Exception e) {
//            System.err.println("❌ SQL Server connection failed:");
//            e.printStackTrace();
//        }
//    }
//}
