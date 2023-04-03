package br.com.bieniek.dynamodbcrud.resolver;

import io.awspring.cloud.dynamodb.DynamoDbTableSchemaResolver;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import static br.com.bieniek.dynamodbcrud.resolver.TableSchemas.MEDICOS_TABLE_SCHEMA;

@Configuration
public class SchemaResolver implements DynamoDbTableSchemaResolver {

    @Override
    public <T> TableSchema resolve(Class<T> clazz, String tableName) {
        return MEDICOS_TABLE_SCHEMA;
    }
}
