SELECT id,
       title,
       description,
       deck_id,
       created_at
FROM room
WHERE id = :id
