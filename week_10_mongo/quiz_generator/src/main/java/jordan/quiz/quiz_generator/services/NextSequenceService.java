package jordan.quiz.quiz_generator.services;

import jordan.quiz.quiz_generator.models.QuizSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class NextSequenceService {
    @Autowired
    private MongoOperations mongo;

    public long getNextSequence(String seqName)
    {
        QuizSequence counter = mongo.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq",1),
                options().returnNew(true).upsert(true),
                QuizSequence.class);
        return counter.getSeq();
    }
}