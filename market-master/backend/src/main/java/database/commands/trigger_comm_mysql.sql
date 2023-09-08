CREATE EVENT sh_deleting_event
    ON SCHEDULE
      EVERY 1 DAY
    DO
      DELETE FROM search_history WHERE DATEDIFF(content_date, CURRENT_TIMESTAMP) < -29;

CREATE EVENT u_deleting_old
    ON SCHEDULE
      EVERY 1 DAY
    DO	  
      DELETE FROM users WHERE DATEDIFF(last_date, CURRENT_TIMESTAMP) < -180;


