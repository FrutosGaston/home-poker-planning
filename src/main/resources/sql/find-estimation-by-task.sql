SELECT id,
       card_id,
       task_id,
       guest_user_id,
       created_at
FROM estimation
WHERE task_id = :task_id
