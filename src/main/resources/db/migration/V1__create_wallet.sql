CREATE TABLE wallet (
                        id          UUID            PRIMARY KEY,
                        owner_id    VARCHAR(255)    NOT NULL,
                        balance     NUMERIC(19, 2)  NOT NULL,
                        version     BIGINT          NOT NULL DEFAULT 0,
                        created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
                        updated_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),

                        CONSTRAINT chk_wallet_balance_non_negative CHECK (balance >= 0)
);

CREATE INDEX idx_wallet_owner_id ON wallet (owner_id);