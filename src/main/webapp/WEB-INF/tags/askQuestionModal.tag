<%@tag description="Question Modal" pageEncoding="UTF-8" %>

<div class="modal fade" id="askQuestionModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ask a Question</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="process_question.jsp" method="post">
                    <div class="mb-3">
                        <label for="questionTopic" class="form-label">Topic</label>
                        <select class="form-select" id="questionTopic" name="questionTopic" required>
                            <option value="topic1">Topic 1</option>
                            <option value="topic2">Topic 2</option>
                            <option value="topic3">Topic 3</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="questionBody" class="form-label">Body</label>
                        <textarea class="form-control" id="questionBody" name="questionBody" rows="3" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>