INSERT INTO maintenance_dates(hardware_uuid, maintenance_date, description)
    VALUES((SELECT uuid FROM hardware WHERE name = 'SearchEngine'), CURRENT_DATE, 'Default maintenance date.');