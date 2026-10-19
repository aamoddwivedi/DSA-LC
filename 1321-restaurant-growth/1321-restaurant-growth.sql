# Write your MySQL query statement below
WITH DailyTotals AS (
    SELECT
        visited_on,
        SUM(amount) AS daily_amount
    FROM Customer
    GROUP BY visited_on
),
MovingWindow AS (
    SELECT
        visited_on,
        SUM(daily_amount) OVER (
            ORDER BY visited_on
            ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
        ) AS amount,
        ROW_NUMBER() OVER (
            ORDER BY visited_on
        ) AS rn
    FROM DailyTotals
)
SELECT
    visited_on,
    amount,
    ROUND(amount / 7, 2) AS average_amount
FROM MovingWindow
WHERE rn >= 7
ORDER BY visited_on;