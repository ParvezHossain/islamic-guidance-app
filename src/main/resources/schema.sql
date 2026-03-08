CREATE TABLE guidance (
      id SERIAL PRIMARY KEY,
      emotion VARCHAR(50) NOT NULL,

      quran_text TEXT,
      quran_reference VARCHAR(50),

      hadith_text TEXT,
      hadith_reference VARCHAR(100)
);